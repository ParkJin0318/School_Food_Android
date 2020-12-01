//
//  StringExtension.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

extension String {
    
    func getTime() -> Date {
        let format = DateFormatter()
        format.dateFormat = "HH:mm"
        return format.date(from: self)!
    }
    
    func filter() -> String {
        var meal =  ""
        var stringBuilder: [String] = []
        
        let lines = self.components(separatedBy: "<br/>")
        for i in lines.indices {
            let line = lines[i]
            if i == lines.count - 1 {
                stringBuilder.append(line)
            } else {
                stringBuilder.append(line)
                stringBuilder.append("\n")
            }
        }
        
        for item in stringBuilder {
            meal += item
        }
        return meal
    }
}
