//
//  ViewController.swift
//  School_Food
//
//  Created by 박진 on 2020/08/25.
//  Copyright © 2020 com.meals.school_food. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class BaseViewController : UIViewController {
    
    let disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCallback()
        bindViewModel()
    }
    
    func configureCallback() { }
    
    func bindViewModel() { }
}
