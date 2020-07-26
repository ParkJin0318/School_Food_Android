//
//  ScheduleViewModel.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Alamofire

class ScheduleViewModel {
    
    let networkClient = NetworkClient()
    let disposeBag = DisposeBag()
    
    let schoolDefaults = SchoolDefaults()
    
    var schedules: [ScheduleInfo] = []
    
    let isSuccess = BehaviorRelay(value: false)
    let isFail = BehaviorRelay(value: false)
    let isLoading = BehaviorRelay(value: false)
    
    func getSchedules(format: String, date: Date) {
        
        self.isLoading.accept(true)
        self.schedules.removeAll()
        
        let scheduleRequest = GetScheduleRequest()
        scheduleRequest.school_id = schoolDefaults.getSchoolDefaults().school_id
        scheduleRequest.office_code = schoolDefaults.getSchoolDefaults().office_code
        scheduleRequest.date = schoolDefaults.getDate(format: format, date: date)
            
        networkClient.getRequest(GetSchedule.Response.self, requestURL: "schedule", params: scheduleRequest)
        .subscribe(
            onNext: { response in
            
                let schedules = response.data?.schedules
                self.schedules = schedules!
                
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
