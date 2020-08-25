//
//  SchoolViewController.swift
//  Meal
//
//  Created by 박진 on 2020/07/15.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import UIKit
import Foundation
import RxCocoa
import RxSwift

class SchoolViewController: BaseViewController {
    
    let viewModel = SchoolViewModel()
    
    var school : SchoolInfo! = nil

    @IBOutlet weak var inputWord: UITextField!
    @IBOutlet weak var searchButton: UIButton!
    @IBOutlet weak var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func configureCallback() {
        self.viewModel.isSuccess.bind { value in
            if value {
                self.collectionView.reloadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isFail.bind { value in
            if value {
                self.collectionView.reloadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isLoading.bind { value in
            if value {
                self.startIndicatingActivity()
            } else {
                self.stopIndicatingActivity()
            }
        }.disposed(by: disposeBag)
    }
    
    override func bindViewModel() {
        searchButton.rx.tap
            .bind(onNext: viewModel.getSchools)
            .disposed(by: disposeBag)
        
        inputWord.rx.text.orEmpty
            .bind(to: viewModel.name)
            .disposed(by: disposeBag)
    }
}

extension SchoolViewController : UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.schools.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "SchoolCell", for: indexPath)
            as? SchoolCell else {
            return UICollectionViewCell()
        }
        cell.layer.cornerRadius = 10
        cell.update(info: viewModel.schools[indexPath.item])
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        school = viewModel.schools[indexPath.item]
        performSegue(withIdentifier: "unwindToSchool", sender: nil)
    }
}
