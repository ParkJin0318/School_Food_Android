//
//  GetMeal.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

struct GetMeal {
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
        var meals : [String?]?
        
        enum DataKey : String, CodingKey{
            case meals
        }
        
        required convenience init(from decoder: Decoder) throws {
            self.init()
            
            let container = try decoder.container(keyedBy: DataKey.self)
            
            self.meals = try container.decodeIfPresent([String?].self, forKey: .meals)
        }
    }
}

