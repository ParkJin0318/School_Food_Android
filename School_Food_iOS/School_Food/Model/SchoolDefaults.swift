//
//  UserDefaults.swift
//  Meal
//
//  Created by 박진 on 2020/07/24.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation

class SchoolDefaults {
    
    func getSchoolDefaults() -> SchoolInfo {
        let school = SchoolInfo()
        
        let schoolName = UserDefaults.standard.string(forKey: "school_name")
        let schoolId = UserDefaults.standard.string(forKey: "school_id")
        let officeCode = UserDefaults.standard.string(forKey: "office_code")
    
        school.school_name = schoolName ?? ""
        school.school_id = schoolId ?? ""
        school.office_code = officeCode ?? ""
        
        return school
    }
    
    func setSchoolDefaults(school: SchoolInfo) -> Void {
        UserDefaults.standard.set(school.school_name, forKey: "school_name")
        UserDefaults.standard.set(school.school_id, forKey: "school_id")
        UserDefaults.standard.set(school.office_code, forKey: "office_code")
    }
}
