//
//  GetMealUseCase.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation
import RxSwift
import RxCocoa

class GetMealUseCase: ParamUseCase {
   
    
    let mealRepository: MealRepository
    
    init() {
        self.mealRepository = MealRepositoryImpl()
    }
    
    typealias Params = GetMealRequest
    typealias T = Single<Meal>
    
    func buildUseCaseObservable(params: GetMealRequest) -> Single<Meal> {
        return mealRepository.getMeal(date: params.date)
    }
}
