//
//  SchoolCell.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import UIKit

class SchoolCell : UICollectionViewCell {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var locateLable: UILabel!
    
    func update(info: SchoolInfo) {
        nameLabel.text = info.school_name
        locateLable.text = info.school_locate
    }
}
