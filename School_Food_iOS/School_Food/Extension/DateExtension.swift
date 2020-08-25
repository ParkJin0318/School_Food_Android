//
//  DateExtension.swift
//  School_Food
//
//  Created by 박진 on 2020/08/25.
//  Copyright © 2020 com.meals.school_food. All rights reserved.
//

import Foundation

extension Date {
    
    func yearDateFormat() -> String {
        let format = DateFormatter()
        format.dateFormat = "yyyyMMdd"
        return format.string(from: self)
    }
    
    func monthDateFormat() -> String {
        let format = DateFormatter()
        format.dateFormat = "yyyyMM"
        return format.string(from: self)
    }
}
