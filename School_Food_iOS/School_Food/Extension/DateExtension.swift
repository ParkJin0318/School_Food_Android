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
    
    func getDateFormat() -> Date {
        let format = DateFormatter()
        format.dateFormat = "HH:mm"
        return format.date(from: format.string(from: self))!
    }
}

extension String {
    
    func getTime() -> Date {
        let format = DateFormatter()
        format.dateFormat = "HH:mm"
        return format.date(from: self)!
    }
    
    func getDateFormat() -> String {
        let beforeFormat = DateFormatter()
        beforeFormat.dateFormat = "yyyyMMdd"
        
        let afterFormat = DateFormatter()
        afterFormat.dateFormat = "yyyy년 M월 d일"
        
        let date = beforeFormat.date(from: self)!
        return afterFormat.string(from: date)
    }
}
