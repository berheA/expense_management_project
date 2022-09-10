export class Reimb {
    constructor(public id:number, public amount:number, public timeSubmitted:string,
        public timeResolved:string, public description:string, public receipt:any, public author:number,
        public resolver:number, public statusID:string, public typeID:string){}
}
