//
//  UIActivityIndicatorViewExtension.swift
//  Meal
//
//  Created by 박진 on 2020/07/23.
//  Copyright © 2020 com.parkjin.meal. All rights reserved.
//

import Foundation
import UIKit

extension UIActivityIndicatorView {
    
    public static func customIndicator(at center: CGPoint) -> UIActivityIndicatorView {
        
        let indicator = UIActivityIndicatorView(frame: CGRect(x: 0.0, y: 0.0, width: 30.0, height: 30.0))
        indicator.layer.cornerRadius = 15
        indicator.center = center
        indicator.hidesWhenStopped = true
        indicator.style = UIActivityIndicatorView.Style.white
        indicator.backgroundColor = UIColor.systemGreen
        
        return indicator
    }
}
