//
//  ScheduleViewController.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import UIKit
import Foundation
import RxCocoa
import RxSwift
import FSCalendar

class ScheduleViewController: UIViewController {
    
    let viewModel = ScheduleViewModel()
    let disposeBag = DisposeBag()
    
    @IBOutlet weak var Calendar: FSCalendar!
    @IBOutlet weak var CollectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCallback()
        
        self.viewModel.getSchedules(format: "yyyyMMdd", date: Date())
    }
}

extension ScheduleViewController {
    
    func configureCallback() {
        self.viewModel.isSuccess.bind { value in
            if value {
                self.CollectionView.reloadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isFail.bind { value in
            if value {
                self.CollectionView.reloadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isLoading.bind { value in
            if value {
                self.startIndicatingActivity()
            } else {
                self.stopIndicatingActivity()
            }
        }.disposed(by: disposeBag)
    }
}

extension ScheduleViewController: FSCalendarDataSource, FSCalendarDelegate {
    
    public func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        self.viewModel.getSchedules(format: "yyyyMMdd", date: date)
    }
}

extension ScheduleViewController: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.schedules.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ScheduleCell", for: indexPath)
            as? ScheduleCell else {
            return UICollectionViewCell()
        }
        cell.update(info: viewModel.schedules[indexPath.item])
        
        return cell
    }
}
