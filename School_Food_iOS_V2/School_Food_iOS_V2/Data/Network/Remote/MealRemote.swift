//
//  MealRemote.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation
import Moya
import RxSwift
import RxCocoa

class MealRemote {
    let provider: MoyaProvider<MealAPI>
    
    init() {
        provider = MoyaProvider()
    }
    
    func getMeal(schoolId: String, officeCode: String, date: String) -> Single<Array<String>> {
        provider.rx.request(.getMeal(schoolId: schoolId, officeCode: officeCode, date: date))
            .map(Response<MealData>.self, using: JSONDecoder())
            .map { response -> Array<String> in
                return response.data.meals
            }
    }
}
