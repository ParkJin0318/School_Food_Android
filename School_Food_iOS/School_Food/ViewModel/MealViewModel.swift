//
//  MealViewModel.swift
//  Meal
//
//  Created by 박진 on 2020/07/25.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Alamofire

class MealViewModel : BaseViewModel {
    
    let schoolDefaults = SchoolDefaults()
    
    var meals: [[String]] = []
    
    let now = BehaviorRelay<Int?>(value: nil)
    let name = BehaviorRelay(value: "")
    let date = BehaviorRelay(value: "")
    let currentDate = BehaviorRelay<Date?>(value: nil)
    
    var morningList: [String] = []
    var lunchList: [String] = []
    var dinnerList: [String] = []
    
    func getName() {
        let name = schoolDefaults.getSchoolDefaults().school_name
        if name == "" {
            self.name.accept("선택된 학교가 없습니다")
        } else {
            self.name.accept(schoolDefaults.getSchoolDefaults().school_name)
        }
    }
    
    func getMeals() {
        
        var mealList : [String] = []
        
        self.isLoading.accept(true)
        self.meals.removeAll()
        
        let mealRequest = GetMealRequest()
        mealRequest.school_id = schoolDefaults.getSchoolDefaults().school_id
        mealRequest.office_code = schoolDefaults.getSchoolDefaults().office_code
        mealRequest.date = self.date.value
        
        networkClient.getRequest(GetMeal.Response.self, requestURL: "meals", params: mealRequest)
        .subscribe(
            onNext: { response in
            
                let data = response.data?.meals
                
                for item in data! {
                    if item != nil {
                        mealList.append(item!)
                    } else {
                        mealList.append("급식이 없습니다")
                    }
                }
                self.setMeal(mealList: mealList)
                
                self.isLoading.accept(false)
                self.isSuccess.accept(true)
            },
            onError: { error in
                self.isLoading.accept(false)
                self.isFail.accept(true)
            }
        ).disposed(by: disposeBag)
    }
    
    func setMeal(mealList : [String]) {
        for i in 0...2 {
            switch i {
                case 0:
                    self.morningList.removeAll()
                    self.morningList.append(contentsOf: mealList[i].components(separatedBy: "<br/>"))
                case 1:
                    self.lunchList.removeAll()
                    self.lunchList.append(contentsOf: mealList[i].components(separatedBy: "<br/>"))
                case 2:
                    self.dinnerList.removeAll()
                    self.dinnerList.append(contentsOf: mealList[i].components(separatedBy: "<br/>"))
                default: return
            }
        }
        self.now.accept(0)
    }
}
