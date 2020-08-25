//
//  MealViewController.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import UIKit
import Foundation
import RxCocoa
import RxSwift

class MealViewController: BaseViewController {
    
    let viewModel = MealViewModel()
    
    var date: Date? = nil
    
    @IBOutlet weak var mealLabel: UILabel!
    @IBOutlet weak var mealView: UIView!
    @IBOutlet weak var segment: UISegmentedControl!
    @IBOutlet weak var datePicker: UIDatePicker!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.viewModel.date.accept(Date().yearDateFormat())
        self.viewModel.getMeals()
    }
    
    override func configureCallback() {
        self.viewModel.isSuccess.bind { value in
            if value {
                self.segment.isHidden = false
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isFail.bind { value in
            if value {
                self.segment.isHidden = true
                self.mealLabel.text = "급식 정보가 존재하지 않습니다"
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.now.bind { value in
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
        
        self.viewModel.currentDate.bind { value in
            if value != nil {
                self.viewModel.date.accept(value!.yearDateFormat())
                self.viewModel.getMeals()
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
    
    override func bindViewModel() {
        segment.rx.selectedSegmentIndex
            .bind(to: viewModel.now)
            .disposed(by: disposeBag)
        
        datePicker.rx.value
            .bind(to: viewModel.currentDate)
            .disposed(by: disposeBag)
    }
}

extension MealViewController {
    
    func showMorning() {
        self.mealLabel.text?.removeAll()
        for item in viewModel.morningList {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func showLunch() {
        self.mealLabel.text?.removeAll()
        for item in viewModel.lunchList {
            mealLabel.text?.append("\(item)\n")
        }
    }
    
    func showDinner() {
        self.mealLabel.text?.removeAll()
        for item in viewModel.dinnerList {
            mealLabel.text?.append("\(item)\n")
        }
    }
}
