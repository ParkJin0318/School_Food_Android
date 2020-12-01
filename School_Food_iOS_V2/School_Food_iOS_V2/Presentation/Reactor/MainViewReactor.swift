//
//  MainViewReactor.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import Foundation
import ReactorKit
import RxCocoa

class MainViewReactor: Reactor {
    
    let getMealUseCase: GetMealUseCase
    
    var initialState: State
    
    var error: PublishRelay<Error>
    
    init() {
        self.getMealUseCase = GetMealUseCase()
        self.initialState = State(mealsData: nil, isMealsLoading: false)
        self.error  = PublishRelay<Error>()
    }
    
    enum Acttion {
        case setInitialMealData
    }
    
    enum Mutation {
        case setMealData(Meal)
        case setIsMealsLoading(Bool)
    }
    
    struct State {
        var mealsData: Meal?
        var isMealsLoading: Bool
    }
    
    func mutate(action: Acttion) -> Observable<Mutation> {
        switch action {
        case .setInitialMealData:
            return Observable.concat([
                Observable.just(Mutation.setIsMealsLoading(true)),
                self.getMealUseCase.buildUseCaseObservable(params: GetMealRequest(date: Date()))
                    .asObservable()
                    .do(onError: { [weak self] error in
                        guard let self = self else { return }
                        self.error.accept(error)
                    })
                    .map { Mutation.setMealData($0) },
                Observable.just(Mutation.setIsMealsLoading(false))
            ])
        }
    }
    
    func reduce(state: State, mutation: Mutation) -> State {
        var state = state
        switch mutation {
        case let .setMealData(mealData):
            state.mealsData = mealData
        case let .setIsMealsLoading(isMealsLoading):
            state.isMealsLoading = isMealsLoading
        }
        return state
    }
}
