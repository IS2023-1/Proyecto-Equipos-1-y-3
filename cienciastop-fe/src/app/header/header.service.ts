import { Injectable } from '@angular/core';
import { Producto } from '../productos/producto';
import { Observable, Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class HeaderService {
    private subject = new Subject<any>();

    sendMessage(matches: Producto[], searchInput: string) {
        this.subject.next({0: matches, 1: searchInput});
    }

    /*clearMessage() {
        this.subject.next();
    }*/

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}