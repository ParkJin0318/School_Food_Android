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

class HomeViewController: UIViewController {
    
    let mealViewModel = MealViewModel()
    let scheduleViewModel = ScheduleViewModel()
    
    let disposeBag = DisposeBag()
    
    let schoolDefaults = SchoolDefaults()

    @IBOutlet weak var mealLabel: UILabel!
    
    @IBOutlet weak var nameLabel: UILabel!
    
    @IBOutlet weak var collection: UICollectionView!
    
    @IBOutlet weak var segment: UISegmentedControl!
    @IBAction func segment(_ sender: UISegmentedControl) {
        selectLoadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureCallback()
        
        let name = schoolDefaults.getSchoolDefaults().school_name
        if name == "" {
            self.nameLabel.text = "선택된 학교가 없습니다"
        } else {
            self.nameLabel.text = schoolDefaults.getSchoolDefaults().school_name
        }
        
        self.mealViewModel.getMeals(format: "yyyyMMdd", date: Date())
        self.scheduleViewModel.getSchedules(format: "yyyyMM", date: Date())
    }
    
    @IBAction func unwindToSchool(_ unwindSegue: UIStoryboardSegue) {
        
        let sourceViewController = unwindSegue.source as? SchoolViewController
        let school = sourceViewController?.school ?? SchoolInfo()
        
        self.schoolDefaults.setSchoolDefaults(school: school)
        self.nameLabel.text = schoolDefaults.getSchoolDefaults().school_name
        self.mealViewModel.getMeals(format: "yyyyMMdd", date: Date())
        self.scheduleViewModel.getSchedules(format: "yyyyMM", date: Date())
    }
}

extension HomeViewController {
    
    func configureCallback() {
        self.mealViewModel.isSuccess.bind { value in
            if value {
                self.segment.isHidden = false
                self.selectLoadData()
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
        
        self.scheduleViewModel.isSuccess.bind { value in
            if value {
                self.collection.reloadData()
            }
        }.disposed(by: disposeBag)
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
        cell.layer.cornerRadius = 8
        cell.update(info: self.scheduleViewModel.schedules[indexPath.item])
        
        return cell
    }
}

extension HomeViewController {
    
    func selectLoadData() {
        mealLabel.text?.removeAll()
        switch self.segment.selectedSegmentIndex {
            case 0:
                self.addMorningData()
            case 1:
                self.addLunchData()
            case 2:
                self.addDinerData()
            
            default: return
        }
    }
    
    func addMorningData() {
        for item in mealViewModel.meals[0] {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    
    func addLunchData() {
        for item in mealViewModel.meals[1] {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func addDinerData() {
        for item in mealViewModel.meals[2] {
            mealLabel.text?.append("\(item)\n")
        }
    }
}
