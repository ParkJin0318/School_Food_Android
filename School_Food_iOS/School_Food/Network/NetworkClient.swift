//
//  NetworkClient.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import RxSwift
import Alamofire

class NetworkClient {

    let SERVER_URL: String!
    let sessionManager: Session

    init() {
        SERVER_URL = "http://kyungwon-server.kro.kr:8080/"
    
        let configuration = URLSessionConfiguration.default
        configuration.timeoutIntervalForRequest = TimeInterval(5)
        sessionManager = Alamofire.Session(configuration: configuration)
    }
     
    func getRequest<T : ResponseProtocol>(_ type: T.Type, requestURL: String, params: Encodable) -> Observable<T> {
        
        return Observable.create { observer -> Disposable in

            self.sessionManager.request(self.SERVER_URL + requestURL, method: .get, parameters: params.dictionary,
                encoding: URLEncoding.default)
                .responseJSON { response in
                    switch response.result {

                    case .success:
                        let decoder = JSONDecoder()
                        let decodeResponse = try! decoder.decode(T.self, from: response.data!)
                        
                        if decodeResponse.status == 200 {
                            observer.onNext(decodeResponse)
                        } else {
                            observer.onError(NetworkError.Custom(message: decodeResponse.message!))
                        }
                    case .failure:
                        observer.onError(NetworkError.Custom(message: "오류가 발생했습니다."))
                    }
            }
            return Disposables.create()
        }
    }
}

enum NetworkError: Error {
    case NetworkError
    case Custom(message: String)
}

