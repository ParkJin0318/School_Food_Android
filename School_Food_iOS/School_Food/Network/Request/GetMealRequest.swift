//
//  GetMealRequest.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class GetMealRequest : Encodable {
    
    var school_id : String = ""
    var office_code : String = ""
    var date : String = ""
    
    convenience init(school_id: String, office_code: String, date: String) {
        self.init()
        
        self.school_id = school_id
        self.office_code = office_code
        self.date = date
    }
    
    private enum CodingKeys: String, CodingKey {
        case school_id = "school_id"
        case office_code = "office_code"
        case date = "date"
    }
}
