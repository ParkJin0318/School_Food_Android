//
//  HomeViewModel.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Alamofire

class SchoolViewModel {
    
    let networkClient = NetworkClient()
    let disposeBag = DisposeBag()
    
    var schools: [SchoolInfo] = []
    
    let isSuccess = BehaviorRelay(value: false)
    let isFail = BehaviorRelay(value: false)
    let isLoading = BehaviorRelay(value: false)
    
    func getSchools(schoolName : String) -> Void {
        
        self.isLoading.accept(true)
        self.schools.removeAll()
        
        let schoolRequest = GetSchoolRequest()
        schoolRequest.school_name = schoolName
        
        networkClient.getRequest(GetSchool.Response.self, requestURL: "search", params: schoolRequest)
            .subscribe(
                onNext: { response in

                    let schools = response.data?.schools!
                    self.schools = schools!
                    
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
