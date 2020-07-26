//
//  Schedule.swift
//  Meal
//
//  Created by 박진 on 2020/07/16.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class ScheduleInfo : Decodable {
    
    var name = ""
    var date = ""
    
    enum ScheduleInfoKeys : String, CodingKey {
        case name = "name"
        case date = "date"
    }
    
    required convenience init(from decoder: Decoder) throws {
        self.init()
        
        let container = try decoder.container(keyedBy: ScheduleInfoKeys.self)
        
        self.name = try container.decode(String.self, forKey: .name)
        self.date = try container.decode(String.self, forKey: .date)
    }
}

