import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../../models/user.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  user: User;

  constructor(public dialog: MatDialogRef<UserCreateComponent>,
              public userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data) {
    this.user = data;
  }

  ngOnInit() {
    this.user = {
      username: '',
      password: '',
      fullName: '',
      userRole: '',
      id: 0
    };
  }

  onNoClick() {
    this.dialog.close();
  }

  onYesClick() {
    console.log(this.user);
    this.userService.create(this.user).subscribe( response => {
      this.dialog.close({ data: this.user });
    });

  }

}
