<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class AppCriticalData extends Model
{
    protected $table='app_critical_data';
    protected $primaryKey = "id";
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'app_key', 'new_user', 'new_device', 'active_user','active_device',
        'pay_user_count', 'pay_money','datetime',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        
    ];
    public $timestamps =true;
}
