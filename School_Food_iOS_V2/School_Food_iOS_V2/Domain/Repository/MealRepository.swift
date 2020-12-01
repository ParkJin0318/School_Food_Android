//
//  MealRepositoryImpl.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation
import RxSwift

protocol MealRepository {
    func getMeal(date: String) -> Single<Meal>
}
