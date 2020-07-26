//
//  GetSchoolRequest.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class GetSchoolRequest : Encodable{
    
    var school_name : String = ""
    
    convenience init(school_name: String) {
        self.init()
        self.school_name = school_name
    }
    
    private enum CodingKeys: String, CodingKey {
        case school_name = "school_name"
    }
}
