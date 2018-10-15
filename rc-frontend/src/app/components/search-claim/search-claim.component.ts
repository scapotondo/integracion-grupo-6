import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-claim',
  templateUrl: './search-claim.component.html',
  styleUrls: ['./search-claim.component.css']
})
export class SearchClaimComponent implements OnInit {

  claimNumber: string;

  constructor() { }

  ngOnInit() {

  }

  enterKey(e) {
    if(e.keyCode == 13){
      this.searchOrder();
    }
  }

  searchOrder() {
    console.log(this.claimNumber);
  }
}
