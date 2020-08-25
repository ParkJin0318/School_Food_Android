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

class SchoolViewModel : BaseViewModel {
    
    var schools: [SchoolInfo] = []
    
    let name = BehaviorRelay(value: "")
    
    func getSchools() {
        
        self.isLoading.accept(true)
        self.schools.removeAll()
        
        let schoolRequest = GetSchoolRequest()
        schoolRequest.school_name = name.value
        
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
