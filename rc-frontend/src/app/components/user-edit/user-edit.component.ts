import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../../models/user.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User;

  constructor(public dialog: MatDialogRef<UserEditComponent>,
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
    this.userService.update(this.user).subscribe( response => {
      this.dialog.close({ data: this.user });
    });

  }

}
