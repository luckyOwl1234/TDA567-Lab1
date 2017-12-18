class EnrollmentStation{

  /*method enroll(fingerprint: int) returns(token: Token)
  {
  }*/

  method enroll(fingerprint : int, clearance : int) returns(token : Token)
  {
  }
}

class Token{

  var id : int;
  var clearance : int;

  method init(id : int, clearance : int)
  requires 0 <= id;
  requires 0 <= clearance <= 3;
  ensures 0 <= id;
  ensures 0 <= clearance <= 3;
  modifies this;
  {
    this.id := id;
    this.clearance := clearance;
  }

  method setClearance(clearance : int) returns (clearanceSet : bool)
  requires 0 <= clearance <= 3;
  ensures 0 <= clearance <= 3;
  modifies this`clearance;
  {
    this.clearance := clearance;
    if this.clearance == clearance
      {clearanceSet := true;}
    else
      {clearanceSet := false;}
  }

  method getClearance() returns(clearance : int){
    return this.clearance;
  }
}

class IDStation{

  var alarmActive: bool;


  method init()
    modifies this;
  {
    alarmActive := false;
  }

  method readFingerPrint() returns (fingerPrint : int)
  {
  }

  method verifyUser(fingerPrint : int) returns (correctUser : bool)
  {
  }

  method verifyClearance(door : Door, token : Token) returns (correctClearance : bool)
  {
  }

  method alarmOn()
  requires this.alarmActive == false;
  ensures this.alarmActive == true;
  modifies this`alarmActive;
  {
    this.alarmActive := true;
  }

  method alarmOff()
  requires this.alarmActive == true;
  ensures this.alarmActive == false;
  modifies this`alarmActive;
  {
    this.alarmActive := false;
  }

  method open(door : Door) returns (doorOpen : bool)
  {
  }

  method close(door : Door) returns (doorClosed : bool)
  {
  }
}

class User{

  var fingerPrint : int;
  var token : Token;

  method init()
  modifies this;
  {}

}

class Door{

  var id : int;
  var requiredClearance : int;

  method init(id : int, clearance : int)
  requires 0 <= clearance <= 3;
  requires 0 <= id;
  ensures this.id >= 0;
  ensures 0 <= this.requiredClearance <= 3;
  modifies this;
  {
    this.id := id;
    requiredClearance := clearance;
  }
}
