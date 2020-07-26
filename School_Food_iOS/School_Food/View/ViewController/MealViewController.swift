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

class MealViewController: UIViewController {
    
    let viewModel = MealViewModel()
    let disposeBag = DisposeBag()
    
    var date: Date? = nil
    
    @IBOutlet weak var mealLabel: UILabel!
    @IBOutlet weak var mealView: UIView!
    
    @IBOutlet weak var segment: UISegmentedControl!
    @IBAction func segment(_ sender: UISegmentedControl) {
        selectLoadData()
    }
    
    @IBAction func DatePicker(_ sender: UIDatePicker) {
        date = sender.date
    }
    
    @IBAction func Check(_ sender: Any) {
        if date != nil {
            self.viewModel.getMeals(format: "yyyyMMdd", date: date!)
        } else {
            self.viewModel.getMeals(format: "yyyyMMdd", date: Date())
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCallback()
        
        self.mealView.layer.cornerRadius = 8
        self.viewModel.getMeals(format: "yyyyMMdd", date: Date())
    }
}

extension MealViewController {
    
    func configureCallback() {
        self.viewModel.isSuccess.bind { value in
            if value {
                self.segment.isHidden = false
                self.selectLoadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isFail.bind { value in
            if value {
                self.segment.isHidden = true
                self.mealLabel.text = "급식 정보가 존재하지 않습니다"
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

extension MealViewController {
    
    func selectLoadData() {
        self.mealLabel.text?.removeAll()
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
           for item in self.viewModel.meals[0] {
               mealLabel.text?.append("\(item)\n")
           }
       }
       
       
       func addLunchData() {
           for item in self.viewModel.meals[1] {
               mealLabel.text?.append("\(item)\n")
           }
       }
       
       func addDinerData() {
           for item in self.viewModel.meals[2] {
               mealLabel.text?.append("\(item)\n")
           }
       }
}
