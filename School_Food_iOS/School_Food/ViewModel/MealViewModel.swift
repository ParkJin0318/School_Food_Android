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

class MealViewModel {
    
    let networkClient = NetworkClient()
    let disposeBag = DisposeBag()
    
    let schoolDefaults = SchoolDefaults()
    
    var meals: [[String]] = []
    
    let isSuccess = BehaviorRelay(value: false)
    let isFail = BehaviorRelay(value: false)
    let isLoading = BehaviorRelay(value: false)
    
    func getMeals(format: String, date: Date) {
        
        var mealList : [String] = []
        
        self.isLoading.accept(true)
        self.meals.removeAll()
        
        let mealRequest = GetMealRequest()
        mealRequest.school_id = schoolDefaults.getSchoolDefaults().school_id
        mealRequest.office_code = schoolDefaults.getSchoolDefaults().office_code
        mealRequest.date = schoolDefaults.getDate(format: format, date: date)
        
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
                
                for i in 0...2 {
                    self.meals.append(mealList[i].components(separatedBy: "<br/>"))
                }
                
                self.isLoading.accept(false)
                self.isSuccess.accept(true)
            },
            onError: { error in
                self.isLoading.accept(false)
                self.isFail.accept(true)
            }
        ).disposed(by: disposeBag)
    }
}
