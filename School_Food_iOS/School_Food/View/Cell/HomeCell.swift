//
//  HomeCell.swift
//  Meal
//
//  Created by 박진 on 2020/07/25.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import UIKit
import Foundation

class HomeCell : UICollectionViewCell {
    
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var scheduleLable: UILabel!
    
    func update(info: ScheduleInfo) {
        dateLabel.text = info.date.getDateFormat()
        scheduleLable.text = info.name
    }
}
