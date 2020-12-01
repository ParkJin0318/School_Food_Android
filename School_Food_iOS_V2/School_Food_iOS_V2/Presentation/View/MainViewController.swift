//
//  ViewController.swift
//  School_Food_iOS_V2
//
//  Created by 박진 on 2020/12/01.
//

import UIKit
import RxSwift
import RxCocoa
import ReactorKit

class MainViewController: UIViewController, StoryboardView {
    
    @IBOutlet weak var mealLabel: UILabel!
    
    var disposeBag: DisposeBag = DisposeBag()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        reactor = MainViewReactor()
        self.getData()
    }
    
    private func getData() {
        getObservable()
        .map { _ in return Reactor.Action.setInitialMealData }
        .bind(to: reactor!.action)
        .disposed(by: disposeBag)
    }
    
    func bind(reactor: MainViewReactor) {
        reactor.state.map { $0.mealsData }
            .filter{ $0 != nil }
            .bind{ [weak self] mealsData in
                guard let self = self else { return }
                self.setMeal(meal: mealsData!)
            }.disposed(by: disposeBag)
        
        reactor.state.map { $0.isMealsLoading }
            .distinctUntilChanged()
            .subscribe(onNext: { [weak self] value in
                guard let self = self else { return }
                if(value){
                    //로딩 중
                }else{
                    // 로딩 끝
                }
            }).disposed(by: disposeBag)
    }
    
    private func getObservable() -> Observable<Void>{
        return Single<Void>.create { single in
            single(.success(Void()))
            return Disposables.create()
        }.asObservable()
    }
    
    private func setMeal(meal: Meal) {
        let now = Date().getTimeFormat()
        
        if "08:20".getTime() > now {
            self.mealLabel.text = meal.breakfast
        } else if "13:20".getTime() > now {
            self.mealLabel.text = meal.lunch
        } else {
            self.mealLabel.text = meal.dinner
        }
    }
}

