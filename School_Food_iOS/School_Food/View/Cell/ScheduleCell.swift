//
//  ScheduleCell.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import UIKit

class ScheduleCell : UICollectionViewCell {
    
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var scheduleLable: UILabel!
    
    func update(info: ScheduleInfo) {
        dateLabel.text = info.date.getDateFormat()
        scheduleLable.text = info.name
    }
}
