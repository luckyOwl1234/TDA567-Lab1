// Dafny 2.0.0.00922 technical preview 0
// Command Line Options: Tokeneer/Tokeneer.dfy
// Tokeneer.dfy

class EnrollmentStation {
  var users: set<int>

  method init()
    modifies this`users
    ensures users == {}
  {
    users := {};
  }

  method enroll(fingerprint: int, clearance: int) returns (token: Token)
    requires 1 <= clearance <= 3
    requires 0 <= fingerprint
    modifies this`users
    decreases fingerprint, clearance
  {
    token := new Token;
    token.init(fingerprint, clearance);
    users := users + {fingerprint};
    return token;
  }
}

class Token {
  //var id: int
  var clearance: int
  var fingerprint: int
  //var invalidated: bool

  method init(/*id: int, */fingerprint: int, clearance: int)
    /*requires 0 <= id*/
    requires 1 <= clearance <= 3
    requires 0 <= fingerprint
    modifies /*this`id, */this`clearance, this`fingerprint/*, this`invalidated*/
    /*ensures 0 <= id*/
    ensures 1 <= clearance <= 3
    /*ensures invalidated == false;*/
    decreases /*id, */clearance
  {
    /*this.id := id;*/
    this.clearance := clearance;
    this.fingerprint := fingerprint;
    /*this.invalidated := false;*/
  }

  method setClearance(clearance: int) returns (clearanceSet: bool)
    requires 0 <= clearance <= 3
    modifies this`clearance
    ensures 0 <= clearance <= 3
    decreases clearance
  {
    this.clearance := clearance;
    if this.clearance == clearance {
      clearanceSet := true;
    } else {
      clearanceSet := false;
    }
  }

  method invalidate() //returns (invalidated: bool)
    requires clearance != 0
    requires this != null
    modifies this`clearance/*, this`invalidated*/
    ensures clearance == 0
  {
    clearance := 0;
  }

  method getClearance() returns (clearance: int)
  {
    return this.clearance;
  }
}

class IDStation {
  var doorOpen: bool
  var doorClearance: int
  var alarmActive: bool

  method init(doorClearance: int)
    requires 1 <= doorClearance <= 3
    modifies this`doorOpen, this`doorClearance, this`alarmActive
    ensures this.doorOpen == false
    ensures this.doorClearance == doorClearance
    ensures this.alarmActive == false
  {
    doorOpen := false;
    this.doorClearance := doorClearance;
    alarmActive := false;
  }

  method tryOpen(token: Token, fingerprint: int) // returns (doorOpen: bool)
    requires doorOpen == false
    requires alarmActive == false
    requires token != null
    //requires 0 <= fingerprint
    //requires 0 <= token.fingerprint
    //requires 1 <= token.clearance <= 3
    modifies this`doorOpen, this`alarmActive, token`clearance
    ensures token.fingerprint == fingerprint && token.clearance >= this.doorClearance ==> doorOpen && !alarmActive && token.clearance == old(token.clearance)
    ensures token.fingerprint == fingerprint && !(token.clearance >= this.doorClearance) ==> doorOpen == old(doorOpen) && alarmActive == old(alarmActive) && token.clearance == old(token.clearance)
    ensures !(token.fingerprint == fingerprint) ==> !doorOpen && alarmActive && token.clearance == 0
  {
    // Verify user integrity
    if token.fingerprint == fingerprint {
      // Verify clearance
      if token.clearance >= this.doorClearance {
        // Open door
        open();
      } else {
        // Do nothing on clearance failure
      }
    } else {
      alarmOn();
      token.invalidate();
    }
  }

  method alarmOn()
    requires this.alarmActive == false
    modifies this`alarmActive
    ensures this.alarmActive == true
  {
    this.alarmActive := true;
  }

  method alarmOff()
    requires this.alarmActive == true
    modifies this`alarmActive
    ensures this.alarmActive == false
  {
    this.alarmActive := false;
  }

  method open()
    requires doorOpen == false
    modifies this`doorOpen
    ensures this.doorOpen == true
  {
    this.doorOpen := true;
  }

  method close()
    requires doorOpen == true
    modifies this`doorOpen
    ensures this.doorOpen == false
  {
    this.doorOpen := false;
  }
}

method TestMain()
{
  //test case 1
  var enrStn := new EnrollmentStation;
  enrStn.init();

  var idStn := new IDStation;
  idStn.init(1);

  var t1 := enrStn.enroll(1,2);

  idStn.tryOpen(t1, 1);
  assert idStn.doorOpen;
  assert !idStn.alarmActive;
/*
  idStn.close();
  assert !idStn.doorOpen;

  //test case 2
  var idStn2 := new IDStation;
  idStn2.init(2);

  var t2 := enrStn.enroll(2,1);

  idStn2.tryOpen(2,t2);
  assert !idStn2.doorOpen;
  assert idStn2.alarmActive;
  assert t2.cl == -1;

  //test case 3
  var idStn3 := new IDStation;
  idStn3.init(1);

  var t3 := enrStn.enroll(3,2);
  var t4 := enrStn.enroll(4,0);

  idStn3.tryOpen(4,t3);
  assert !idStn3.doorOpen;
  assert idStn3.alarmActive;
  assert t3.cl == -1;
*/
}
