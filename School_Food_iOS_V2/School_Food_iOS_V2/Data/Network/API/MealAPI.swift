//
//  MealAPI.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Moya

enum MealAPI {
    case getMeal(schoolId: String, officeCode: String, date: String)
}

extension MealAPI: TargetType {
    var baseURL: URL {
        return URL(string: Constants.HOST)!
    }
    
    var path: String {
        switch self {
        case .getMeal:
            return "meals"
        }
    }
    
    var method: Method {
        switch self {
        case .getMeal:
            return .get
        }
    }
    
    var sampleData: Data {
        return Data()
    }
    
    var task: Task {
        switch self {
        case let .getMeal(schoolId, officeCode, date) :
            return .requestParameters(parameters: ["school_id": schoolId, "office_code": officeCode, "date": date],
                                      encoding: URLEncoding.queryString)
        }
    }
    
    var validationType: Moya.ValidationType {
        return .successAndRedirectCodes
    }
    
    var headers: [String : String]? {
        let headers = ["Content-Type": "application/json"]
        return headers
    }
}
