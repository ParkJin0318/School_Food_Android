//
//  File.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class SchoolInfo : Decodable {
    
    var school_name = ""
    var school_locate = ""
    var office_code = ""
    var school_id = ""
    
    enum SchoolInfoKeys : String, CodingKey {
        case school_name = "school_name"
        case school_locate = "school_locate"
        case office_code = "office_code"
        case school_id = "school_id"
    }
    
    required convenience init(from decoder: Decoder) throws {
        self.init()
        
        let container = try decoder.container(keyedBy: SchoolInfoKeys.self)
        
        self.school_name = try container.decode(String.self, forKey: .school_name)
        self.school_locate = try container.decode(String.self, forKey: .school_locate)
        self.office_code = try container.decode(String.self, forKey: .office_code)
        self.school_id = try container.decode(String.self, forKey: .school_id)
    }
}
