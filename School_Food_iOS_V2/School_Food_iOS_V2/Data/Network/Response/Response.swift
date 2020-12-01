//
//  Response.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

class Response<T: Codable>: Codable {
    var status : Int
    var message : String
    var data: T
}
