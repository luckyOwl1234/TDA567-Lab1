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
    requires fingerprint !in users
    modifies this`users
    ensures fresh(token)
    ensures token.clearance == clearance
    ensures token.fingerprint == fingerprint
    ensures users == old(users) + {fingerprint}
    ensures fingerprint in users //TODO: Prettify
    ensures token != null
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
    /*ensures 1 <= clearance <= 3*/
    ensures this.clearance == clearance
    ensures this.fingerprint == fingerprint
    /*ensures invalidated == false;*/
    decreases /*id, */clearance
  {
    /*this.id := id;*/
    this.clearance := clearance;
    this.fingerprint := fingerprint;
    /*this.invalidated := false;*/
  }

  method setClearance(clearance: int) /*returns (clearanceSet: bool)*/
    requires 0 <= clearance <= 3
    modifies this`clearance
    ensures this.clearance == clearance
    decreases clearance
  {
    this.clearance := clearance;
  }

  method invalidate() //returns (invalidated: bool)
    //requires clearance != 0
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

method main()
{
  // Test 1 (Higher clearance than required, Open door, No alarm)
  var enrollmentStation := new EnrollmentStation;
  enrollmentStation.init();

  var IDStation := new IDStation;
  IDStation.init(1);

  var firstUser := enrollmentStation.enroll(1,2);

  IDStation.tryOpen(firstUser, 1);
  assert IDStation.doorOpen;
  assert !IDStation.alarmActive;

  IDStation.close();
  assert !IDStation.doorOpen;

  // Test 2 (Lower clearance than required, Door remains closed, No alarm, Valid token)
  var IDStation2 := new IDStation;
  IDStation2.init(2);

  var secondUser := enrollmentStation.enroll(2,1);

  IDStation2.tryOpen(secondUser,2);
  assert !IDStation2.doorOpen;
  assert !IDStation2.alarmActive;
  assert secondUser.clearance == 1;

  // Test 3 (Wrong user, Door remains closed, Sound alarm, Invalidate token)
  var IDStation3 := new IDStation;
  IDStation3.init(1);

  var thirdUser := enrollmentStation.enroll(3,2);
  var fourthUser := enrollmentStation.enroll(4,1);

  IDStation3.tryOpen(thirdUser,4);
  assert !IDStation3.doorOpen;
  assert IDStation3.alarmActive;
  assert thirdUser.clearance == 0;

  // Test 4 (Same door twice, Door opens both times, No alarm)

  var IDStation4 := new IDStation;
  IDStation4.init(2);

  var fifthUser := enrollmentStation.enroll(5,2);

  IDStation4.tryOpen(fifthUser,5);

  assert IDStation4.doorOpen;
  assert !IDStation4.alarmActive;

  IDStation4.close();

  IDStation4.tryOpen(fifthUser,5);

  assert IDStation4.doorOpen;
  assert !IDStation4.alarmActive;

  // TODO: "Works" but doesn't compile. How to implement?
  // Test 5 (Two users, Same fingerprint, Different clearance)
/*
  var IDStation5 := new IDStation;
  IDStation5.init(3);

  var sixthUser := enrollmentStation.enroll(6,3);
  var seventhUser := enrollmentStation.enroll(6,2);

  IDStation5.tryOpen(sixthUser,6);

  assert IDStation5.doorOpen;
  assert !IDStation5.alarmActive;
  assert sixthUser.clearance == 3;

  IDStation5.close();

  IDStation5.tryOpen(seventhUser,6);

  assert !IDStation5.doorOpen;
  assert !IDStation5.alarmActive;
  assert seventhUser.clearance == 2;
*/

  // Test 6 (Change clearance, Door opens, No alarm, Valid token)
  var IDStation6 := new IDStation;
  IDStation6.init(2);

  var eightUser := enrollmentStation.enroll(8,1);
  
  IDStation6.tryOpen(eightUser,8);

  assert !IDStation6.doorOpen;
  assert !IDStation6.alarmActive;
  assert eightUser.clearance == 1;

  eightUser.setClearance(3);

  IDStation6.tryOpen(eightUser,8);

  assert IDStation6.doorOpen;
  assert !IDStation6.alarmActive;
  assert eightUser.clearance == 3;
}

