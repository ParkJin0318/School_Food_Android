//
//  GetSchool.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class GetSchool {
    class Response : ResponseProtocol{
        var data: Data?
        
        enum ResponseKey : String, CodingKey {
            case status
            case message
            case data
        }
        
        required convenience init(from decoder: Decoder) throws {
            self.init()
            
            let container = try decoder.container(keyedBy: ResponseKey.self)
            
            self.status = try container.decode(Int.self, forKey: .status)
            self.message = try container.decode(String.self, forKey: .message)
            self.data = try container.decodeIfPresent(Data.self, forKey: .data)
            
        }
    }
    class Data : Decodable {
        var schools : [SchoolInfo]?
        
        enum DataKey : String, CodingKey{
            case schools
        }
        
        required convenience init(from decoder: Decoder) throws {
            self.init()
            
            let container = try decoder.container(keyedBy: DataKey.self)
            
            self.schools = try container.decodeIfPresent([SchoolInfo].self, forKey: .schools)
        }
    }
}

