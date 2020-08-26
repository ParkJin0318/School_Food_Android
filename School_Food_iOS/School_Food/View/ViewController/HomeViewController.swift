//
//  ViewController.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import UIKit
import Foundation
import RxCocoa
import RxSwift

class HomeViewController: BaseViewController {
    
    let mealViewModel = MealViewModel()
    let scheduleViewModel = ScheduleViewModel()
    
    let schoolDefaults = SchoolDefaults()

    @IBOutlet weak var mealLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var segment: UISegmentedControl!
    @IBOutlet weak var collection: UICollectionView!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.getInfo()
    }
    
    @IBAction func unwindToSchool(_ unwindSegue: UIStoryboardSegue) {
        let sourceViewController = unwindSegue.source as? SchoolViewController
        let school = sourceViewController?.school ?? SchoolInfo()
        
        self.schoolDefaults.setSchoolDefaults(school: school)
        self.getInfo()
    }
    
    override func configureCallback() {
        self.mealViewModel.isSuccess.bind { value in
            if value {
                self.segment.isHidden = false
            }
        }.disposed(by: disposeBag)
        
        self.mealViewModel.isFail.bind { value in
            if value {
                self.segment.isHidden = true
                self.mealLabel.text = "급식 정보가 존재하지 않습니다"
            }
        }.disposed(by: disposeBag)
        
        self.mealViewModel.isLoading.bind { value in
            if value {
                self.startIndicatingActivity()
            } else {
                self.stopIndicatingActivity()
            }
        }.disposed(by: disposeBag)
        
        self.mealViewModel.now.bind { value in
            switch value {
                case 0:
                    self.showMorning()
                case 1:
                    self.showLunch()
                case 2:
                    self.showDinner()
                
                default: return
            }
        }.disposed(by: disposeBag)
        
        self.scheduleViewModel.isSuccess.bind { value in
            if value {
                self.collection.reloadData()
            }
        }.disposed(by: disposeBag)
    }
    
    override func bindViewModel() {
        segment.rx.value
            .bind(to: mealViewModel.now)
            .disposed(by: disposeBag)
        
        mealViewModel.name
            .bind(to: nameLabel.rx.text)
            .disposed(by: disposeBag)
    }
}

extension HomeViewController : UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return scheduleViewModel.schedules.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "HomeCell", for: indexPath)
            as? HomeCell else {
            return UICollectionViewCell()
        }
        cell.layer.cornerRadius = 10
        cell.update(info: self.scheduleViewModel.schedules[indexPath.item])
        
        return cell
    }
}

extension HomeViewController {
    
    func showMorning() {
        self.mealLabel.text?.removeAll()
        self.segment.selectedSegmentIndex = 0
        
        for item in mealViewModel.morningList {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func showLunch() {
        self.mealLabel.text?.removeAll()
        self.segment.selectedSegmentIndex = 1
        
        for item in mealViewModel.lunchList {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func showDinner() {
        self.mealLabel.text?.removeAll()
        self.segment.selectedSegmentIndex = 2
        
        for item in mealViewModel.dinnerList {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func getInfo() {
        self.mealViewModel.getName()
        self.mealViewModel.getMeals(date: Date().yearDateFormat())
        self.scheduleViewModel.getSchedules(date: Date().monthDateFormat())
    }
}
