//
//  GetMealRequest.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

class GetMealRequest: Codable {
    let date: String
    
    init(date: Date) {
        self.date = date.toString()
    }
}
