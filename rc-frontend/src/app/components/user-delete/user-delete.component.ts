import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  user: User;

  constructor(public dialog: MatDialogRef<UserDeleteComponent>,
              public userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data) {
    this.user = data;
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialog.close();
  }

  onYesClick() {
    //this.userService.delete(this.user).subscribe( response => {
      this.dialog.close({ data: this.user });
   // });

  }

}
