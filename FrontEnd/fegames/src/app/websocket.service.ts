import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket!: WebSocket;
  private socket$!: Subject<any>;

  constructor() {}

  public connect(url: string): Subject<any> {
    this.socket = new WebSocket(url);

    const observable = new Observable<MessageEvent>((observer) => {
      this.socket.onmessage = (event) => observer.next(event);
      this.socket.onerror = (event) => observer.error(event);
      this.socket.onclose = (event) => observer.complete();

      return () => {
        this.socket.close();
      };
    });

    const socketSubject = new Subject<MessageEvent>();
    const socketDataSubject = new Subject<any>();

    const observer = {
      next: (data: Object) => {
        if (this.socket.readyState === WebSocket.OPEN) {
          this.socket.send(JSON.stringify(data));
        }
      },
    };

    socketSubject.subscribe(observer);
    observable.subscribe((messageEvent) => {
      socketDataSubject.next(JSON.parse(messageEvent.data));
    });

    return socketDataSubject;
  }

  public disconnect() {
    if (this.socket) {
      this.socket.close();
    }
  }

  sendMovement(idMatch: number, movement: string) {
    const message = {
      type: 'MOVEMENT',
      idMatch: idMatch,
      movement: movement,
    };
    this.socket$.next(message);
  }

  sendChat(target: string, message: string) {
    const chatMessage = {
      type: 'CHAT',
      target: target,
      message: message,
    };
    this.socket$.next(chatMessage);
  }

  sendBroadcast(message: String) {
    const broadcastMessage = {
      type: "BROADCAST",
      message: message,
    };
    console.log("11111111")
    console.log(JSON.stringify(broadcastMessage))
    console.log(broadcastMessage)


    this.socket$.next(JSON.stringify(broadcastMessage));
  }
  
}
