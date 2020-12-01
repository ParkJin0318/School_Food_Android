//
//  DateExtension.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

extension Date {
    
    func toString(format: String = "yyyyMMdd") -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        dateFormatter.locale = Locale(identifier: "ko")
        
        return dateFormatter.string(from: self)
    }
    
    func getTimeFormat() -> Date {
        let format = DateFormatter()
        format.dateFormat = "HH:mm"
        return format.date(from: format.string(from: self))!
    }
}
