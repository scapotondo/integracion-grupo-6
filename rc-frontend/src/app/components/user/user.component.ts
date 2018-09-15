import { Component, OnInit} from '@angular/core';
import { User } from '../../models/user.model';
import {UIContext} from '../../ui.context';
import {UserService} from '../../services/user.service';
import {MatDialog, MatDialogConfig} from "@angular/material";
import {UserDeleteComponent} from "../user-delete/user-delete.component";
import {UserEditComponent} from "../user-edit/user-edit.component";
import {UserCreateComponent} from "../user-create/user-create.component";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  readonly USER_NOT_FOUND = -1;

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

  createUser(): void {
    this.dialog.open(UserCreateComponent,{
      width: '250px',
    }).afterClosed()
      .subscribe(response => {
        this.users.push(response);
      });
  }

  editUser(user): void {
    this.dialog.open(UserEditComponent,{
      width: '250px',
      data: user
    }).afterClosed()
      .subscribe(response => {
        const index: number = this.users.findIndex(x => x.id == response.data.id);
        if (index !== this.USER_NOT_FOUND)  {
          this.users[index] = response;
        }
      });
  }

  deleteUser(user): void {
    this.dialog.open(UserDeleteComponent,{
      width: '250px',
      data: user
    }).afterClosed()
      .subscribe(response => {
        const index: number = this.users.findIndex(x => x.id == response.data.id);
        if (index !== this.USER_NOT_FOUND)  {
          this.users.splice(index, 1);
        }
      });
  }

}
