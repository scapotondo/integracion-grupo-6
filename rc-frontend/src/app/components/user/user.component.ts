import { Component, OnInit} from '@angular/core';
import { User } from '../../models/user.model';
import {UIContext} from '../../ui.context';
import {UserService} from '../../services/user.service';
import {MatDialog, MatDialogConfig} from "@angular/material";
import {UserDeleteComponent} from "../user-delete/user-delete.component";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['fullName', 'username', 'role', 'action'];

  users: User[];
  dialogConfig = new MatDialogConfig();

  constructor(
    public uiContext: UIContext,
    public userService: UserService,
    public dialog: MatDialog,
  ) {

    this.uiContext.setTittle('Usuarios');
    this.dialogConfig.disableClose = true;
    this.dialogConfig.autoFocus = true;
  }

  ngOnInit() {
    this.userService.findAll()
      .subscribe( data => {
        this.users = data;
      });
  }

  deleteUser(user): void {
    const that = this;
    this.dialog.open(UserDeleteComponent,{
      width: '250px',
      data: user
    }).afterClosed()
      .subscribe(response => {
        console.log(this.users)
        const index: number = that.users.findIndex(x => x.id == response.data.id);
        if (index !== -1)  {
          this.users.splice(index, 1);
        }
      });
  }

}
