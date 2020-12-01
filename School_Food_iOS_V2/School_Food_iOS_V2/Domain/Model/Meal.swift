//
//  File.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

struct Meal {
    let breakfast: String?
    let lunch: String?
    let dinner: String?
    
    init(breakfast: String?, lunch: String?, dinner: String?) {
        if (breakfast != nil) {
            self.breakfast = breakfast?.filter()
        } else {
            self.breakfast = "급식이 없습니다"
        }
        
        if (lunch != nil) {
            self.lunch = lunch?.filter()
        } else {
            self.lunch = "급식이 없습니다"
        }
        
        if (dinner != nil) {
            self.dinner = dinner?.filter()
        } else {
            self.dinner = "급식이 없습니다"
        }
    }
}
