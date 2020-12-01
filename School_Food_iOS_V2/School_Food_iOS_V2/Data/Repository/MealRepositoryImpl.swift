//
//  MealRepositoryImpl.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation
import RxSwift
import RxCocoa

class MealRepositoryImpl: MealRepository {
    
    let remote: MealRemote
    
    init() {
        remote = MealRemote()
    }
    
    func getMeal(date: String) -> Single<Meal> {
        let school = SchoolController.getInstance()
        return remote.getMeal(schoolId: school.getSchoolId(), officeCode: school.getOfficeCode(), date: date).map { meals in
            Meal(breakfast: meals[0], lunch: meals[1], dinner: meals[2])
        }
    }
}
