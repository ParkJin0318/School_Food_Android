//
//  SchoolController.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

class SchoolController {
    let preference = UserDefaults.standard
    
    let schoolName = "school_name"
    let schoolId = "school_id"
    let officeCode = "office_code"
    let schoolLocation = "school_location"
    
    func setSchoolInfo(school: School) {
        preference.set(school.name, forKey: schoolName)
        preference.set(school.id, forKey: schoolId)
        preference.set(school.officeCode, forKey: officeCode)
        preference.set(school.location, forKey: schoolLocation)
    }
    
    func getSchoolId() -> String {
        return preference.value(forKey: schoolId) as? String ?? ""
    }
    
    func getSchoolName() -> String {
        return preference.value(forKey: schoolName) as? String ?? ""
    }
    
    func getOfficeCode() -> String {
        return preference.value(forKey: officeCode) as? String ?? ""
    }
    
    func getSchoolLocation() -> String {
        return preference.value(forKey: schoolLocation) as? String ?? ""
    }
}

extension SchoolController {
    static var schoolController:SchoolController!
    
    static func getInstance() -> SchoolController {
        if(SchoolController.schoolController == nil){
            SchoolController.schoolController = SchoolController()
        }
        return .schoolController
    }
}
