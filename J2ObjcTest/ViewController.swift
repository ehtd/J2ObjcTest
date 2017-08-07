//
//  ViewController.swift
//  J2ObjcTest
//
//  Created by Ernesto Torres on 8/2/17.
//  Copyright © 2017 Ernesto Torres. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    let fetcher = IOEFetcher(nsString: "https://hacker-news.firebaseio.com/v0/")

    override func viewDidLoad() {
        super.viewDidLoad()

        view.backgroundColor = UIColor.red
        DispatchQueue.global().async { [weak self] in
            if let strongSelf = self, let fetcher = strongSelf.fetcher {
                let result: String? = fetcher.fetch("topstories.json")
                print(result ?? "")
            }
        }
        view.backgroundColor = UIColor.green
    }
}

