//
//  BaseViewModel.swift
//  School_Food
//
//  Created by 박진 on 2020/08/25.
//  Copyright © 2020 com.meals.school_food. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import Alamofire

class BaseViewModel {
    
    let networkClient = NetworkClient()
    let disposeBag = DisposeBag()
    
    let isSuccess = BehaviorRelay(value: false)
    let isFail = BehaviorRelay(value: false)
    let isLoading = BehaviorRelay(value: false)
}
