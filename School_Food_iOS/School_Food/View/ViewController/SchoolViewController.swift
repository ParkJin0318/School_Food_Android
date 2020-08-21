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

class SchoolViewController: UIViewController{
    
    let viewModel = SchoolViewModel()
    let disposeBag = DisposeBag()
    
    var school : SchoolInfo! = nil

    @IBOutlet weak var CollectionView: UICollectionView!
    @IBOutlet weak var inputWord: UITextField!
    
    @IBAction func searchEvent(_ sender: UIButton) {
        self.viewModel.getSchools(schoolName: self.inputWord.text!)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureCallback()
    }
}

extension SchoolViewController {
    
    func configureCallback() {
        self.viewModel.isSuccess.bind { value in
            if value {
                self.CollectionView.reloadData()
            }
        }.disposed(by: disposeBag)
        
        self.viewModel.isFail.bind { value in
            if value {
                self.CollectionView.reloadData()
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
