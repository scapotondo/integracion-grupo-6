import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { User } from '../../models/user.model';
import {UIContext} from '../../ui.context';
import {UserService} from '../../services/user.service';
import {MatDialog, MatDialogConfig} from "@angular/material";
import {UserDeleteComponent} from "../user-delete/user-delete.component";
import {UserEditComponent} from "../user-edit/user-edit.component";
import {UserCreateComponent} from "../user-create/user-create.component";
import {forEach} from "@angular/router/src/utils/collection";

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
    public dialog: MatDialog
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
    let that = this;
    this.dialog.open(UserCreateComponent,{
      width: '250px',
    }).afterClosed()
      .subscribe(response => {
        let users2 = [];
        that.users.forEach(x => users2.push(x));
        users2.push(response.data);
        that.users = users2;
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
    let that = this;
    this.dialog.open(UserDeleteComponent,{
      width: '250px',
      data: user
    }).afterClosed()
      .subscribe(response => {
        let users2 = [];
        that.users.forEach(x =>{
          if(x.id != response.data.id){
            users2.push(x);
          }
        });

        that.users = users2;
      });
  }

}
