//
//  ParamsUseCase.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation

protocol ParamUseCase {
    associatedtype Params
    associatedtype T
    func buildUseCaseObservable(params:Params) -> T
}
